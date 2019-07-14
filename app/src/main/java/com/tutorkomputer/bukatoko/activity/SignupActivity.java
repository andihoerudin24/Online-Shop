package com.tutorkomputer.bukatoko.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.adapter.TabAdapter;
import com.tutorkomputer.bukatoko.fragment.auth.SigninFragment;
import com.tutorkomputer.bukatoko.fragment.auth.SignupFragment;

public class SignupActivity extends AppCompatActivity {

  public static TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);

        addTab(viewPager);
        tabLayout.setupWithViewPager(viewPager);


        getSupportActionBar().setTitle("Pengguna Baru");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void addTab(ViewPager viewPager){
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addfragment(new SignupFragment(),"Daftar");
        adapter.addfragment(new SigninFragment(),"Masuk");
        viewPager.setAdapter(adapter);
    }
}
