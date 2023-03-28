package pe.edu.idat.apppatitasidat.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import pe.edu.idat.apppatitasidat.retrofit.PatitasCliente;
import pe.edu.idat.apppatitasidat.retrofit.request.RequestLogin;
import pe.edu.idat.apppatitasidat.retrofit.request.RequestRegistro;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseLogin;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseRegistro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends AndroidViewModel {

    public MutableLiveData<ResponseLogin> loginMutableLiveData
            = new MutableLiveData<>();

    public MutableLiveData<ResponseRegistro> registroMutableLiveData
            = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
    }

    public void autenticarUsuario(
            RequestLogin requestLogin){
        new PatitasCliente().getINSTANCE().login(requestLogin)
                .enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        loginMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void registrarUsuario(
            RequestRegistro requestRegistro){
        new PatitasCliente().getINSTANCE().registrar(requestRegistro)
                .enqueue(new Callback<ResponseRegistro>() {
                    @Override
                    public void onResponse(Call<ResponseRegistro> call, Response<ResponseRegistro> response) {
                        registroMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<ResponseRegistro> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

}
