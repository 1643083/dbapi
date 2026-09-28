package com.example.fragmentos.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragmentos.Fragments.BuscadorPersonajeFragment;
import com.example.fragmentos.Fragments.ListaPersonajesFragment;
import com.example.fragmentos.Fragments.ListaPlanetasFragment;

//1. herencia > 2. implementar métodos (interfaz) > 3. constructor
public class DashboardPageAdapter extends FragmentStateAdapter {

    //sin cambios...
    public DashboardPageAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new BuscadorPersonajeFragment();
            case 1: return new ListaPersonajesFragment();
            default: return new ListaPlanetasFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
