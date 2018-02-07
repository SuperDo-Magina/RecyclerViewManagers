package com.superdo.magina.recyclerviewmanagers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.superdo.magina.managers.card.CardItemTouchHelperCallback
import com.superdo.magina.managers.card.CardLayoutManager
import com.superdo.magina.managers.card.OnSwipeListener
import com.superdo.magina.recyclerviewmanagers.bean.ItemBean
import kotlinx.android.synthetic.main.activity_card.*
import java.util.*

/**
 * <pre>
 *
 *      author LYB
 *      time   18/2/5 下午5:56
 *      des
 *
 * </pre>
 */

class CardActivity : AppCompatActivity() {

    private val list = ArrayList<ItemBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        initData()

        initView()
    }

    private fun initData() {

        setTitle("卡牌")

        list.add(ItemBean(R.mipmap.img_01, "宝宝医院"))
        list.add(ItemBean(R.mipmap.img_02, "生日派对"))
        list.add(ItemBean(R.mipmap.img_03, "糖果工厂"))
        list.add(ItemBean(R.mipmap.img_04, "照顾小宝宝"))
        list.add(ItemBean(R.mipmap.img_05, "美食餐厅"))
        list.add(ItemBean(R.mipmap.img_06, "宝宝甜品店"))
        list.add(ItemBean(R.mipmap.img_07, "宝宝小画板"))
        list.add(ItemBean(R.mipmap.img_08, "秒秒爱干净"))
        list.add(ItemBean(R.mipmap.img_09, "宝宝超市"))
        list.add(ItemBean(R.mipmap.img_10, "出行安全"))
        list.add(ItemBean(R.mipmap.img_11, "中华美食"))
        list.add(ItemBean(R.mipmap.img_12, "居家安全"))

    }

    private fun initView() {

        val adapter = MyAdapter()

        recyclerView.adapter = adapter

        val cardCallback = CardItemTouchHelperCallback(recyclerView.adapter, list)
        cardCallback.setOnSwipedListener(object : OnSwipeListener<ItemBean> {

            override fun onSwiping(viewHolder: RecyclerView.ViewHolder, ratio: Float, direction: Int) {
                viewHolder.itemView.alpha = 1 - Math.abs(ratio) * 0.2f
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, o: ItemBean?, direction: Int) {
                viewHolder.itemView.alpha = 1f
            }

            override fun onSwipedClear() {
                recyclerView.postDelayed({
                    initData()
                    recyclerView.adapter.notifyDataSetChanged()
                }, 3000)
            }
        })
        val touchHelper = ItemTouchHelper(cardCallback)
        val manager = CardLayoutManager(recyclerView, touchHelper)
        recyclerView.layoutManager = manager
        touchHelper.attachToRecyclerView(recyclerView)
        adapter.notifyDataSetChanged()


    }

    private inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_card, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
            val bean = list[position]
            holder!!.ivIcon.setImageResource(bean.resId)
            holder.tvName.text = bean.name

        }

        override fun getItemCount(): Int = list.size
    }

    private inner class MyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivIcon: ImageView = itemView.findViewById(R.id.iv)
        var tvName: TextView = itemView.findViewById(R.id.tv_name)

    }

}

