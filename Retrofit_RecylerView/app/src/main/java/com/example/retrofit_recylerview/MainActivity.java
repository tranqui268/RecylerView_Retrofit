package com.example.retrofit_recylerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.retrofit_recylerview.Adapter.ProductAdapter;
import com.example.retrofit_recylerview.Api.ProductLoader;
import com.example.retrofit_recylerview.object.Product;
import com.example.retrofit_recylerview.object.Rating;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Product>> {

    RecyclerView recyclerView;
    static ArrayList<Product> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,MainActivity.this).forceLoad();
        }
        getProducts("2");
    }

    private void getProducts(String id){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            getSupportLoaderManager().restartLoader(0,null,MainActivity.this);

        }

    }


    @NonNull
    @Override
    public Loader<List<Product>> onCreateLoader(int id,Bundle args) {
        Log.e("INFO","new productLoader");
        return new ProductLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished( Loader<List<Product>> loader, List<Product> data) {
        try {
            for(Product o : data){
                listData.add(o);
            }
            recyclerView = findViewById(R.id.myRecylerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ProductAdapter(this,MainActivity.this,listData));

        }catch (Exception e){
            Log.e("ERROR","Call Api Fail");
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset( Loader<List<Product>> loader) {

    }
}