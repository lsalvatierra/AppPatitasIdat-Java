package pe.edu.idat.apppatitasidat.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pe.edu.idat.apppatitasidat.retrofit.PatitasCliente;
import pe.edu.idat.apppatitasidat.retrofit.request.RequestLogin;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseLogin;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseMascota;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaViewModel extends AndroidViewModel {

    public MutableLiveData<List<ResponseMascota>> listMascotaMutableLiveData
            = new MutableLiveData<>();


    public MascotaViewModel(@NonNull Application application) {
        super(application);
    }

    public void listarMascotas(){
        new PatitasCliente().getINSTANCE().listarMascotas()
                .enqueue(new Callback<List<ResponseMascota>>() {
                    @Override
                    public void onResponse(Call<List<ResponseMascota>> call, Response<List<ResponseMascota>> response) {
                        listMascotaMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<ResponseMascota>> call, Throwable t) {

                    }
                });
    }


}
