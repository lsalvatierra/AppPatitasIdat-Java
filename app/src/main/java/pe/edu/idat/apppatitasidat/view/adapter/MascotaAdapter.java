package pe.edu.idat.apppatitasidat.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pe.edu.idat.apppatitasidat.databinding.ItemMascotaBinding;
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseMascota;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.ViewHolder> {

    List<ResponseMascota> listMascota = new ArrayList<>();

    @NonNull
    @Override
    public MascotaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMascotaBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    public void setMascotas(List<ResponseMascota> lista){
        listMascota.addAll(lista);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaAdapter.ViewHolder holder, int position) {
        final ResponseMascota responseMascota = listMascota.get(position);
        holder.binding.tvcontacto.setText(responseMascota.getContacto());
        holder.binding.tvfechaperdida.setText(responseMascota.getFechaperdida());
        holder.binding.tvlugar.setText(responseMascota.getLugar());
        holder.binding.tvnommascota.setText(responseMascota.getNommascota());
        Glide.with(holder.binding.getRoot())
                .load(responseMascota.getUrlimagen())
                .into(holder.binding.ivmascota);

    }

    @Override
    public int getItemCount() {
        return listMascota.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMascotaBinding binding;
        public ViewHolder(ItemMascotaBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
