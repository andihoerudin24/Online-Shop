package com.tutorkomputer.bukatoko.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.adapter.TabAdapter;
import com.tutorkomputer.bukatoko.fragment.trans.PaidFragment;
import com.tutorkomputer.bukatoko.fragment.trans.UnpaidFragment;

public class TransActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);

        addTab(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setTitle("Pembelian");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    private void  addTab(ViewPager viewPager){
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addfragment(new UnpaidFragment(),"Tertunda");
        adapter.addfragment(new PaidFragment(),"Proses");
        viewPager.setAdapter(adapter);
    }
}
