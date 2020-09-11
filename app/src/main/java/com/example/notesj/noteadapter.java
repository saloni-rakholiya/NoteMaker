package com.example.notesj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class noteadapter extends RecyclerView.Adapter<noteadapter.noteholder> {

    private List<note> notes=new ArrayList<>();

    @NonNull
    @Override
    public noteholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.noteitem,parent,false);
        return new noteholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull noteholder holder, int position) {

        note current=notes.get(position);
        holder.title.setText(current.getTitle());
        holder.desc.setText(current.getDescription());
        holder.priority.setText(String.valueOf(current.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setnotes(List<note> notes)
    {
        this.notes=notes;
        notifyDataSetChanged();
    }

    class noteholder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView desc;
        private TextView priority;

        public noteholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.name);
            desc=itemView.findViewById(R.id.desc);
            priority=itemView.findViewById(R.id.priority);
        }
    }
}
