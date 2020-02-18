package com.spyrdonapps.coinlore.utils

import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView

private const val LAYOUT_MANAGER_INSTANCE_STATE_TAG = "LLM"

class RecyclerLifecycleUtils {

    private var layoutManagerInstanceState: Parcelable? = null

    fun handleSavedInstanceStateIfNeeded(savedInstanceState: Bundle?) {
        savedInstanceState
            ?.getParcelable<Parcelable>(LAYOUT_MANAGER_INSTANCE_STATE_TAG)
            ?.let { state: Parcelable -> layoutManagerInstanceState = state }
    }

    fun saveRecyclerState(outState: Bundle, recyclerView: RecyclerView) {
        outState.putParcelable(
            LAYOUT_MANAGER_INSTANCE_STATE_TAG,
            recyclerView.layoutManager?.onSaveInstanceState()
        )
    }

    fun restoreRecyclerState(recyclerView: RecyclerView) {
        layoutManagerInstanceState?.let {
            recyclerView.layoutManager?.onRestoreInstanceState(it)
            layoutManagerInstanceState = null
        }
    }
}
