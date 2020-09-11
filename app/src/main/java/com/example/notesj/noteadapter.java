package com.example.notesj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class noteadapter extends ListAdapter<note, noteadapter.noteholder> {

    //private List<note> notes = new ArrayList<>();

    private onitemlistener onitemlistener;

    public noteadapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<note> DIFF_CALLBACK = new DiffUtil.ItemCallback<note>() {
        @Override
        public boolean areItemsTheSame(@NonNull note oldItem, @NonNull note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull note oldItem, @NonNull note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public noteholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noteitem, parent, false);
        return new noteholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull noteholder holder, int position) {

        note current = getItem(position);
        holder.title.setText(current.getTitle());
        holder.desc.setText(current.getDescription());
        holder.priority.setText(String.valueOf(current.getPriority()));

    }

   /* @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setnotes(List<note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }*/

    public note getnote(int pos) {
        return getItem(pos);
    }

    class noteholder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private TextView priority;

        public noteholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            priority = itemView.findViewById(R.id.priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (onitemlistener != null && pos != RecyclerView.NO_POSITION)
                        onitemlistener.onitemclick(getItem(pos));
                }
            });
        }
    }

    public interface onitemlistener {
        void onitemclick(note note);
    }

    public void setonitemclicklistener(onitemlistener onitemlistener) {

        this.onitemlistener = onitemlistener;

    }
}
