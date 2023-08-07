package com.blockchain.coinpes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Offers>  data;

    public Adapter(List<Offers> data) {
        this.data = data;
    }
  public static class ViewHolder extends RecyclerView.ViewHolder{
      TextView username;
      TextView positive;
      TextView method;

      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          // Initialize your item views here
          // for any view that will be set as you render a row
          username = itemView.findViewById(R.id.feed_username);
          positive = itemView.findViewById(R.id.feed_positive);
          method = itemView.findViewById(R.id.method);
      }
      public void bind(Offers offers) throws JSONException {
          // Bind data to your item views here
          JSONObject offer_user = new JSONObject(offers.getOffer_user());
          JSONObject offer_data = new JSONObject(offers.getOffer_string());
          username.setText(offer_data.optString("username"));
          method.setText(offer_data.optString("method"));
          positive.setText(offer_user.optString("feed_positive"));
          System.out.println(offers.getOffer_string());
          System.out.println(offers.getOffer_user());

      }
  }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
    // Set item views based on your views and data model
        try {
            holder.bind(data.get(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
