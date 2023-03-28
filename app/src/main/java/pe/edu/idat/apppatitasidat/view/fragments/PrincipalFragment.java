package pe.edu.idat.apppatitasidat.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.edu.idat.apppatitasidat.R;
import pe.edu.idat.apppatitasidat.databinding.FragmentPrincipalBinding;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseMascota;
import pe.edu.idat.apppatitasidat.view.adapter.MascotaAdapter;
import pe.edu.idat.apppatitasidat.viewmodel.MascotaViewModel;


public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;
    private MascotaViewModel mascotaViewModel;
    private MascotaAdapter adapter = new MascotaAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPrincipalBinding.inflate(inflater, container,
                false);
        mascotaViewModel = new ViewModelProvider(requireActivity())
                .get(MascotaViewModel.class);
        binding.rvmascotas.setLayoutManager(
                new LinearLayoutManager(requireActivity()));
        binding.rvmascotas.setAdapter(adapter);
        mascotaViewModel.listarMascotas();
        mascotaViewModel.listMascotaMutableLiveData.observe(getViewLifecycleOwner(),
                new Observer<List<ResponseMascota>>() {
                    @Override
                    public void onChanged(List<ResponseMascota> responseMascotas) {
                        adapter.setMascotas(responseMascotas);
                    }
                });
        return binding.getRoot();
    }
}