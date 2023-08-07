package com.blockchain.coinpes.ui.dashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blockchain.coinpes.Adapter;
import com.blockchain.coinpes.MyHttpClient;
import com.blockchain.coinpes.Offers;
import com.blockchain.coinpes.R;
import com.blockchain.coinpes.databinding.FragmentDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class DashboardFragment extends Fragment {
    private static final String[] HEADER_NAMES = {"username", "token"};
    private static final String[] HEADER_VALUES = {"NastinesCodsi423", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3QiLCJzdWIiOiIzZDQxYmUzODA2MWEwNDFmNGY1N2UxZDQzZTMwN2Q5OTQxOTVhZGUyODA0ZmM1MWU2NDU4MWFmNTNiMzgzYjY1IiwiaWF0IjoxNjg2MDYzODI1LCJleHAiOjE2ODg2OTM4MjV9.4aNn7RVBmhMU4nEhWuB-yCcV-ZXOODbs4IIXbs0fgbc"};
    List<Offers> offer_list;
    private FragmentDashboardBinding binding;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view); // Use rootView.findViewById
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Add data to dataList
       new HttpTask().execute();
        return rootView;
    }
    private Headers createHeaders() {
        Headers.Builder builder = new Headers.Builder();
        for (int i = 0; i < DashboardFragment.HEADER_NAMES.length; i++) {
            builder.add(DashboardFragment.HEADER_NAMES[i], DashboardFragment.HEADER_VALUES[i]);
        }
        return builder.build();
    }
    private class HttpTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            Headers headers = createHeaders();
            try {
                return MyHttpClient.makePostRequest(getResources().getString(R.string.app_url)+"/api/coin/v1/getOffers",headers,new JSONObject().put("type","sell").toString());

            }catch (Exception e){
             return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                // Process the response...Runs on the UI thread after doInBackground.
                System.out.println(response);
                try {
                    JSONObject data = new JSONObject(response);
                    JSONArray posts = data.optJSONArray("responseMessage");
                    offer_list = new ArrayList<>();

                    for (int i = 0; i < posts.length(); i++) {
                        JSONObject post = posts.optJSONObject(i);
                        Offers item = new Offers();
                        item.setOffer_string(post.optString("offer"));
                        item.setOffer_user(post.optString("user"));
                        item.setPositive_feedback(post.optString("positive_feedback"));
                        offer_list.add(item);
                    }

                    // Initialize your adapter and set it to the RecyclerView
                    Adapter adapter = new Adapter(offer_list);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                // Handle the error

            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}