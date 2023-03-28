package pe.edu.idat.apppatitasidat.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatitasCliente {

    private static final String BASE_URL = "http://www.kreapps.biz/patitas/";
    //private PatitasServicio patitasServicio;
    private PatitasServicio INSTANCE;

    public PatitasCliente(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INSTANCE = retrofit.create(PatitasServicio.class);
    }

    public PatitasServicio getINSTANCE(){
        return INSTANCE;
    }
}
