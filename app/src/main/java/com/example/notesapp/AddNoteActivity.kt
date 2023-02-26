package com.example.notesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.databinding.ActivityAddNoteBinding
import com.example.notesapp.models.Note
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding
    private lateinit var note : Note
    private lateinit var old_note : Note
    var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {

            old_note = intent.getSerializableExtra("currentNote") as Note
            binding.etTitle.setText(old_note.title)
            binding.etDesc.setText(old_note.desc)
            isUpdate = true
        }
        catch (e:Exception){
            e.printStackTrace()
        }
        binding.save.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDesc.text.toString()

            if (title.isNotEmpty() || desc.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                if (isUpdate){
                    note = Note(
                        old_note.id,title,desc,formatter.format(Date())
                    )
                }
                else{
                    note =  Note(
                        null,title,desc,formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }
            else{
                Toast.makeText(this@AddNoteActivity,"Please Enter Data",Toast.LENGTH_SHORT).show()
            }
        }

        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}