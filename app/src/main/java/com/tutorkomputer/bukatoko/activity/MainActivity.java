package com.tutorkomputer.bukatoko.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.activity.dialog.LoginDialog;
import com.tutorkomputer.bukatoko.adapter.ProductAdapter;
import com.tutorkomputer.bukatoko.data.database.PrefsManager;
import com.tutorkomputer.bukatoko.data.model.Product;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;
import com.tutorkomputer.bukatoko.utils.AuthState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MaterialSearchView searchView;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    Menu menu;



    private void getProducts(){
        swipeRefresh.setRefreshing(true);
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<Product> call = apiInterface.getProducts();
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                List<Product.Data> products = product.getProducts();

                //Log.e("_logsise",String.valueOf(products.size()));

                recyclerView.setAdapter(new ProductAdapter(MainActivity.this, products));
                for(int i=0; i<products.size(); i++){
                    Log.e("_logsise",products.get(i).getProduct());
                 }

                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        App.sessPref=App.prefsManager.getUserDetail();
        Log.e("token_firebase",App.sessPref.get(PrefsManager.SESS_TOKEN));

        searchView =findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();

        recyclerView=findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefresh=findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setAdapter(null);
                getProducts();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();

        if (menu !=null){
            if (App.prefsManager.isLogggedIn()){
                AuthState.isLoggedIn(menu);
            }else{
                AuthState.isLoggedOut(menu);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {

            if (App.prefsManager.isLogggedIn()){
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }else{
                new LoginDialog().showLoginDialog(MainActivity.this,menu);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
//            Toast.makeText(getApplicationContext(),"Notif",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this,SignupActivity.class));
        }
        else if (id == R.id.nav_notiv) {
            startActivity(new Intent(MainActivity.this,NotifActivity.class));

        } else if (id == R.id.nav_trans) {
            startActivity(new Intent(MainActivity.this,TransActivity.class));
            //Toast.makeText(getApplicationContext(),"Transaksi",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_profile) {
//            Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this,ProfileActivity.class));
        } else if (id == R.id.nav_logout) {
//            Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(MainActivity.this,SignupActivity.class));

            AuthState.udpateToken(MainActivity.this,"");

            App.prefsManager.logoutUser();
            AuthState.isLoggedOut(menu);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
