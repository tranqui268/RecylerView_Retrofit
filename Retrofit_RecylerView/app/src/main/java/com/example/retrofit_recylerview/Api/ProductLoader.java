package com.example.retrofit_recylerview.Api;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.retrofit_recylerview.object.Product;

import java.util.List;

import retrofit2.Call;

public class ProductLoader extends AsyncTaskLoader<List<Product>> {
    private String id;
    private Context context;
    public ProductLoader( Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Product> loadInBackground() {
        Log.e("DataLoader","loadInBackground " + id);
        Call<List<Product>> call = ApiService.apiService.getProductFromApi();
        try{
           return call.execute().body();
        }catch (Exception e){
            Log.e("DataLoader","Call Api Fail");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
      forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
