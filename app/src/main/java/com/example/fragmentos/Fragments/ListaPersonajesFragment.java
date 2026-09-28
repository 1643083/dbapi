package com.example.fragmentos.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fragmentos.Adapters.AdapterPersonajes;
import com.example.fragmentos.Model.Personaje;
import com.example.fragmentos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListaPersonajesFragment extends Fragment {

    RecyclerView RVPersonajes;
    ArrayList<Personaje> listPersonajes = new ArrayList<>();
    AdapterPersonajes adapterPersonajes;
    RequestQueue requestQueue;

    private final String URL = "https://dragonball-api.com/api/characters";

    int paginaActual = 1;
    int totalPaginas = 1;
    boolean cargando = false;


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_personajes, container,false);

        RVPersonajes = view.findViewById(R.id.RVPersonajes);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        RVPersonajes.setLayoutManager(layoutManager);

        adapterPersonajes = new AdapterPersonajes(listPersonajes);
        RVPersonajes.setAdapter(adapterPersonajes);

        requestQueue = Volley.newRequestQueue(requireContext());

        // Cargar primera página
        obtenerDatosWS(1);

        // Detectar cuando llegamos al final
        RVPersonajes.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        int totalItems = layoutManager.getItemCount();
                        int ultimoElemento = layoutManager.findLastVisibleItemPosition();

                        if (!cargando && paginaActual < totalPaginas && ultimoElemento >= totalItems - 2) {
                            obtenerDatosWS(paginaActual + 1);
                        }
                    }
                }
        );
        return view;
    }


    private void obtenerDatosWS(int pagina) {

        if (cargando) { return; }

        cargando = true;

        String url = URL + "?page=" + pagina + "&limit=10";

        JsonObjectRequest request =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        response -> {
                            try {
                                JSONArray items = response.getJSONArray("items");
                                JSONObject meta = response.getJSONObject("meta");
                                totalPaginas = meta.getInt("totalPages");
                                paginaActual = meta.getInt("currentPage");
                                int inicio = listPersonajes.size();
                                for (int i = 0; i < items.length(); i++) {
                                    JSONObject obj = items.getJSONObject(i);
                                    listPersonajes.add(
                                            new Personaje(
                                                    obj.getString("id"),
                                                    obj.getString("name"),
                                                    obj.getString("ki"),
                                                    obj.getString("gender"),
                                                    obj.getString("race"),
                                                    obj.getString("affiliation")
                                            )
                                    );
                                }
                                adapterPersonajes.notifyItemRangeInserted(inicio, items.length());
                                cargando = false;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                cargando = false;
                            }
                        },
                        error -> {
                            error.printStackTrace();
                            cargando = false;
                            Toast.makeText(requireContext(),"Error cargando personajes",Toast.LENGTH_SHORT).show();
                        }
                );
        requestQueue.add(request);
    }
}
