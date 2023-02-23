package es.nemamo.zoonerea.RecyclerViewAnimal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.R;

public class ARecyclerViewAdapter extends RecyclerView.Adapter<ARecyclerViewAdapter.ViewHolder> {

    private List<RecyclerViewAnimal> list;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;
    private Context context;

    public ARecyclerViewAdapter(Context context, List<RecyclerViewAnimal> list) {
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @NonNull
    @Override
    public ARecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_animal_element, parent, false);
        view.setOnClickListener(onClickListener);
        return new ARecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ARecyclerViewAdapter.ViewHolder holder, int position) {
        holder.idAnimal.setText(String.valueOf(list.get(position).getAnimal().getId()));
        holder.animalName.setText(list.get(position).getAnimal().getName());
        holder.family.setText(list.get(position).getAnimal().getFamily());
        holder.specie.setText(list.get(position).getAnimal().getSpecie());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idAnimal;
        TextView animalName;
        TextView family;
        TextView specie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idAnimal = itemView.findViewById(R.id.idAnimal);
            animalName = itemView.findViewById(R.id.animalName);
            family = itemView.findViewById(R.id.family);
            specie = itemView.findViewById(R.id.specie);
        }
    }
}