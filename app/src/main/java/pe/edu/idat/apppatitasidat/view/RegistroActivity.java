package pe.edu.idat.apppatitasidat.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pe.edu.idat.apppatitasidat.R;
import pe.edu.idat.apppatitasidat.databinding.ActivityRegistroBinding;
import pe.edu.idat.apppatitasidat.retrofit.request.RequestRegistro;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseRegistro;
import pe.edu.idat.apppatitasidat.viewmodel.AuthViewModel;

public class RegistroActivity extends AppCompatActivity implements
        View.OnClickListener {

    private ActivityRegistroBinding binding;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        authViewModel = new ViewModelProvider(this)
                .get(AuthViewModel.class);
        binding.btnirlogin.setOnClickListener(this);
        binding.btnregistrarse.setOnClickListener(this);
        authViewModel.registroMutableLiveData.observe(this,
                new Observer<ResponseRegistro>() {
                    @Override
                    public void onChanged(ResponseRegistro responseRegistro) {
                        validarRegistroUsuario(responseRegistro);
                    }
                });

    }
    private void validarRegistroUsuario(ResponseRegistro responseRegistro) {
        Toast.makeText(getApplicationContext(),
                responseRegistro.getMensaje(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnirlogin){
            startActivity(new Intent(RegistroActivity.this,
                    MainActivity.class));
        }else{
            registrarUsuario();
        }
    }
    private void registrarUsuario() {
        RequestRegistro requestRegistro = new RequestRegistro();
        requestRegistro.setNombres(binding.etnombrereg.getText().toString());
        requestRegistro.setApellidos(binding.etapellidoreg.getText().toString());
        requestRegistro.setEmail(binding.etemailreg.getText().toString());
        requestRegistro.setCelular(binding.etcelularreg.getText().toString());
        requestRegistro.setUsuario(binding.etusuarioreg.getText().toString());
        requestRegistro.setPassword(binding.etpasswordreg.getText().toString());
        authViewModel.registrarUsuario(requestRegistro);
    }
}