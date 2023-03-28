package pe.edu.idat.apppatitasidat.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pe.edu.idat.apppatitasidat.R;
import pe.edu.idat.apppatitasidat.databinding.ActivityMainBinding;
import pe.edu.idat.apppatitasidat.retrofit.request.RequestLogin;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseLogin;
import pe.edu.idat.apppatitasidat.viewmodel.AuthViewModel;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private ActivityMainBinding binding;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnlogin.setOnClickListener(this);
        binding.btnregistrar.setOnClickListener(this);
        authViewModel = new ViewModelProvider(this)
                .get(AuthViewModel.class);
        authViewModel.loginMutableLiveData.observe(this,
                new Observer<ResponseLogin>() {
                    @Override
                    public void onChanged(ResponseLogin responseLogin) {
                        validarAutenticacion(responseLogin);
                    }
                });
    }
    private void validarAutenticacion(ResponseLogin responseLogin) {
        if(responseLogin.getRpta()){
            startActivity(new Intent(MainActivity.this,
                    HomeActivity.class));
            finish();
        }else{
            Toast.makeText(this, responseLogin.getMensaje(),
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnlogin){
            autenticarUsuario();
        }else{
            startActivity(new Intent(MainActivity.this,
                    RegistroActivity.class));
        }
    }
    public void autenticarUsuario(){
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setUsuario(binding.etusuario.getText().toString());
        requestLogin.setPassword(binding.etpassword.getText().toString());
        authViewModel.autenticarUsuario(requestLogin);
    }
}