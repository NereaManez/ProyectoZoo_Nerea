package es.nemamo.zoonerea.RecyclerViewHabitat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.nemamo.zoonerea.Model.Habitat;
import es.nemamo.zoonerea.R;

public class HRecyclerViewAdapter extends RecyclerView.Adapter<HRecyclerViewAdapter.ViewHolder> {

    private List<Habitat> list;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;
    private Context context;

    public HRecyclerViewAdapter(Context context, List<Habitat> list) {
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @NonNull
    @Override
    public HRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_habitat_element, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.idHabitat.setText(String.valueOf(list.get(position).getId()));
        holder.typeHabitat.setText(list.get(position).getHabitatType());
        holder.descHabitat.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idHabitat;
        TextView typeHabitat;
        TextView descHabitat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idHabitat = itemView.findViewById(R.id.idHabitat);
            typeHabitat = itemView.findViewById(R.id.typeHabitat);
            descHabitat = itemView.findViewById(R.id.descHabitat);
        }
    }
}
