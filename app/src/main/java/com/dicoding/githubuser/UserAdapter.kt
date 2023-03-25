package com.dicoding.githubuser

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubuser.api.response.ItemsItem
import com.dicoding.githubuser.databinding.ItemUserBinding

class UserAdapter(private val listUser: ArrayList<ItemsItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listUser[position]
        Glide
            .with(holder.itemView.context)
            .load(data.avatarUrl)
            .circleCrop()
            .into(holder.binding.itemPhoto)
        holder.binding.tvName.text = data.login

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, DetailUserActivity::class.java)
            i.putExtra("username", data.login)
            holder.itemView.context.startActivity(i)
        }
    }


}