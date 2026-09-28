package com.example.fragmentos.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fragmentos.R;

import org.json.JSONException;
import org.json.JSONObject;

//1. herencia
public class BuscadorPersonajeFragment extends Fragment {

    EditText edtBuscarId, edtId, edtName, edtKi, edtRace, edtGender, edtAffiliation;
    Button btnBuscar;
    JSONObject jsonPersonaje; //datos que enviaremos a ws
    RequestQueue requestQueue;
    private final String URL = "https://dragonball-api.com/api/characters";

    //2. constructor
    public BuscadorPersonajeFragment() { }

    private void loadUI(@NonNull View view){
        edtBuscarId = view.findViewById(R.id.edtBuscarId);
        edtId = view.findViewById(R.id.edtId);
        edtName = view.findViewById(R.id.edtName);
        edtKi = view.findViewById(R.id.edtKi);
        edtRace = view.findViewById(R.id.edtRace);
        edtGender = view.findViewById(R.id.edtGender);
        edtAffiliation = view.findViewById(R.id.edtAffiliation);

        requestQueue = Volley.newRequestQueue(requireContext());

        btnBuscar = view.findViewById(R.id.btnBuscar);
    }

    private void buscar(){
        String id = edtBuscarId.getText().toString().trim();

        if (id.isEmpty()) {
            edtBuscarId.setError("Ingresa un ID");
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL + "/" + id,
                null,
                response -> {
                    try {
                        edtId.setText(response.getString("id"));
                        edtName.setText(response.getString("name"));
                        edtKi.setText(response.getString("ki"));
                        edtRace.setText(response.getString("race"));
                        edtGender.setText(response.getString("gender"));
                        edtAffiliation.setText(response.getString("affiliation"));
                    } catch (JSONException e){
                        throw new RuntimeException(e);
                    }
                },
                error -> Toast.makeText(requireContext(), "personaje no encontrado", Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(request);
    }

    //3. construcción fragmento

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //3.1 asociar XML con el backend Java
        return inflater.inflate(R.layout.fragment_buscador_personajes, container, false);
    }


    //4. control componentes
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.loadUI(view);

        btnBuscar.setOnClickListener(v -> { buscar(); });
    }
}
