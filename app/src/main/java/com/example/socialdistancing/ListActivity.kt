package com.example.socialdistancing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socialdistancing.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var activityListBinding: ActivityListBinding
    private lateinit var adapter: EntryAdapter
    private lateinit var listViewModel: ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityListBinding = ActivityListBinding.inflate(layoutInflater)
        setContentView(activityListBinding.root)
        supportActionBar?.title="List Pelanggaran"
        adapter = EntryAdapter()
        adapter.notifyDataSetChanged()

        activityListBinding.rvUser.layoutManager = LinearLayoutManager(this@ListActivity)
        activityListBinding.rvUser.adapter =adapter

        listViewModel =ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(ListViewModel::class.java)
        listViewModel.getListEntry()

        adapter.setOnItemClickCallback(object : EntryAdapter.OnItemClickCallback{
            override fun onItemClicked(url: String) {
                val moveWithObjectIntent = Intent(this@ListActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_USER, url)
                startActivity(moveWithObjectIntent)
            }
        })
        listViewModel.getEntry().observe(this, Observer{ Entry ->
            if (Entry != null) {
                adapter.setData(Entry)
            }
        })
    }

}