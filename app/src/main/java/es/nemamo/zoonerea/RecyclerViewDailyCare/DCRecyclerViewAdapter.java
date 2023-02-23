package es.nemamo.zoonerea.RecyclerViewDailyCare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.nemamo.zoonerea.R;

public class DCRecyclerViewAdapter extends RecyclerView.Adapter<DCRecyclerViewAdapter.ViewHolder> {

    private List<RecyclerViewDailyCare> list;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;
    private Context context;

    public DCRecyclerViewAdapter(Context context, List<RecyclerViewDailyCare> list) {
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @NonNull
    @Override
    public DCRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_daily_care_element, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DCRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.taskTypeTitle.setText(list.get(position).getTaskType().getTitle());
        holder.taskTypeDesc.setText(list.get(position).getTaskType().getDescription());
        holder.animalName.setText(list.get(position).getAnimal().getName());
        holder.diaryCareDate.setText(list.get(position).getDiaryCare().getDate());
        holder.taskFrequency.setText(list.get(position).getTask().getFrequency());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTypeTitle;
        TextView taskTypeDesc;
        TextView animalName;
        TextView diaryCareDate;
        TextView taskFrequency;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskTypeTitle = itemView.findViewById(R.id.taskTypeTitle);
            taskTypeDesc = itemView.findViewById(R.id.taskTypeDesc);
            animalName = itemView.findViewById(R.id.animalNameDC);
            diaryCareDate = itemView.findViewById(R.id.diaryCareDate);
            taskFrequency = itemView.findViewById(R.id.taskFrequency);
        }
    }
}
