package com.example.restapi;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.restapi.databinding.ActivitySearchMahasiswaBinding;

public class SearchMahasiswaActivity extends AppCompatActivity {

    private EditText edtChecNrp;
    private Button btnSearch;
    private ProgressBar progressBar;
    private TextView tvNrp;
    private TextView tvNama;
    private TextView tvEmail;
    private TextView tvJurusan;
    private List<Mahasiswa> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mahasiswa);

        edtChecNrp = findViewById(R.id.edtChckNrp);
        btnSearch = findViewById(R.id.btnSearch);
        progressBar = findViewById(R.id.progressBar);
        tvNrp = findViewById(R.id.tvValNrp);
        tvNama = findViewById(R.id.tvValNama);
        tvEmail = findViewById(R.id.tvValEmail);
        tvJurusan = findViewById(R.id.tvValJurusan);

        mahasiswaList = new ArrayList<>();

        btnSearch.setOnClickListener(view -> {
            showLoading(true);
            String nrp = edtChecNrp.getText().toString();
            if (nrp.isEmpty()){ edtChecNrp.setError("Silahakan Isi nrp terlebih dahulu");
                showLoading(false);
            }else{
                Call<MahasiswaResponse> client = ApiConfig.getApiService().getMahasiswa(nrp);
                client.enqueue(new Callback<MahasiswaResponse>() {
                    @Override
                    public void onResponse(Call<MahasiswaResponse> call, Response<MahasiswaResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null){ showLoading(false);
                                mahasiswaList = response.body().getData();
                                setData(mahasiswaList);
                            }
                        } else {
                            if (response.body() != null) {
                                Log.e("", "onFailure: " + response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MahasiswaResponse> call, Throwable t) {
                        showLoading(false);
                        Log.e("Error Retrofit", "onFailure: " + t.getMessage());
                    }
                });
            }
        });
    }

    private void setData(List<Mahasiswa> mahasiswaList) {
        tvNrp.setText(mahasiswaList.get(0).getNrp());
        tvNama.setText(mahasiswaList.get(0).getNama());
        tvEmail.setText(mahasiswaList.get(0).getEmail());
        tvJurusan.setText(mahasiswaList.get(0).getJurusan());
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
        progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}