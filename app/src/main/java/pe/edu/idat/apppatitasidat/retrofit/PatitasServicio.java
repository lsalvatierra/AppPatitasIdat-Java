package pe.edu.idat.apppatitasidat.retrofit;

import java.util.List;

import pe.edu.idat.apppatitasidat.retrofit.request.RequestLogin;
import pe.edu.idat.apppatitasidat.retrofit.request.RequestRegistro;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseLogin;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseMascota;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseRegistro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PatitasServicio {

    @POST("login.php")
    public Call<ResponseLogin> login(@Body RequestLogin requestLogin);

    @PUT("persona.php")
    public Call<ResponseRegistro> registrar(@Body RequestRegistro requestRegistro);

    @GET("mascotaperdida.php")
    public Call<List<ResponseMascota>> listarMascotas();


}
