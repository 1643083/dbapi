package com.example.fragmentos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fragmentos.Adapters.DashboardPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private void loadUI(){
        tabLayout = findViewById(R.id.tabLayout); //opciones
        viewPager = findViewById(R.id.viewPager); //visor de fragments
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.loadUI();

        DashboardPageAdapter adapter = new DashboardPageAdapter(this);
        viewPager.setAdapter(adapter);

        //creación de elementos en el tabLayout
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: tab.setText("buscador"); break;
                    case 1: tab.setText("personajes"); break;
                    case 2: tab.setText("planetas"); break;
                }
            }
        }).attach();
    }
}