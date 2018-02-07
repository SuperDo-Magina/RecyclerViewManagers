# RecyclerViewManagers

## 引用

1.moven

      maven {
            url 'https://jitpack.io'
      }
 
2.在 build.gradle 中添加依赖

    compile 'com.github.SuperDo-Magina:RecyclerViewManagers:1.0.0'
    compile 'com.android.support:recyclerview-v7:xx.x.x'
    
## 使用

1.实现拖拽效果
	
	1.创建itemTouchHelper
	val callback = ItemDragHelperCallback(adapter, list)
	val itemTouchHelper = ItemTouchHelper(callback)
	
	2.关联RecyclerView
   	itemTouchHelper.attachToRecyclerView(recyclerView)
    
2.实现卡牌效果
	 
	val cardCallback = CardItemTouchHelperCallback(adapter, list)
	val touchHelper = ItemTouchHelper(cardCallback)
    val manager = CardLayoutManager(recyclerView, touchHelper)
    recyclerView.layoutManager = manager
    touchHelper.attachToRecyclerView(recyclerView)
