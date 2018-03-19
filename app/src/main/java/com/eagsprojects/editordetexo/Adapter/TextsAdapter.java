package com.eagsprojects.editordetexo.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.eagsprojects.editordetexo.Model.TextModel;
import com.eagsprojects.editordetexo.R;

import java.util.List;

/**
 * Created by Emmanuel on 24/1/2018.
 */

public class TextsAdapter extends RecyclerView.Adapter<TextsAdapter.TextViewHolder>{

    private List<TextModel> textList;
    private final OnItemClickListener clickListener;
    private final OnLongItemClickListener longClickListener;
    private static int cvColor;

    public TextsAdapter (List<TextModel> textList,int cvColor,OnItemClickListener clickListener, OnLongItemClickListener longClickListener){
        this.textList = textList;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
        this.cvColor = cvColor;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_texts,parent,false);
        TextViewHolder viewHolder = new TextViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.title.setText(textList.get(position).getTitle());
        holder.subtext.setText(textList.get(position).getSubtext());
        holder.bind(textList.get(position),clickListener,longClickListener);
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder{

        private TextView title,subtext;
        private EditText text;
        private CardView cardView;

        public TextViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            cardView.setCardBackgroundColor(cvColor);
            title = itemView.findViewById(R.id.texttitle);
            subtext = itemView.findViewById(R.id.subtext);
            text = itemView.findViewById(R.id.edittext);
        }

        public void bind(final TextModel textModel, final OnItemClickListener clickListener, final OnLongItemClickListener longClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(textModel);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.onItemLongClick(textModel);
                    return true;
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(TextModel item);
    }

    public interface OnLongItemClickListener{
        void onItemLongClick(TextModel item);
    }

}



