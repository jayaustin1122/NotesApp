package com.example.notesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.models.Note
import org.w3c.dom.Text
import kotlin.random.Random

class NoteAdapter (private val context:Context , val listener:NotesItemClickListener) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val notesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notesList[position]

        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.desc.text = currentNote.desc
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(notesList[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(notesList[holder.adapterPosition],holder.notes_layout)
            true
        }

    }
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val notes_layout = itemView.findViewById<CardView>(R.id.cardlayout)
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val desc = itemView.findViewById<TextView>(R.id.tvDesc)
        val date = itemView.findViewById<TextView>(R.id.tvDate)
    }
    fun randomColor():Int{
        val list = ArrayList<Int>()
        list.add(R.color.one)
        list.add(R.color.two)
        list.add(R.color.three)
        list.add(R.color.five)
        list.add(R.color.five)
        list.add(R.color.six)
        list.add(R.color.seven)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)

        return list[randomIndex]
    }
    interface NotesItemClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note,cardView: CardView)
    }
    fun updateList(newList : List<Note>){
        fullList.clear()
        fullList.addAll(newList)
        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()
    }
    fun filterList(search : String){
        notesList.clear()

        for (item in fullList){
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.desc?.lowercase()?.contains(search.lowercase()) == true){

                notesList.add(item)
            }
        }
        notifyDataSetChanged()
    }
}