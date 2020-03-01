package com.asligresik.absensi.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

class Helper {
    companion object{
        fun showAlertDialog(context: Context, title: String, message: String, positiveBtnClick: OnCallbackAlertDialog): AlertDialog.Builder {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("OK"){
                dialog, _ ->
                run {
                    positiveBtnClick.positiveBtnClick()
                    dialog.dismiss()
                }
            }
            return builder
        }

        fun showConfirmDialog(context: Context, title: String, message: String, dialogBtnClick: OnCallbackConfirmDialog): AlertDialog.Builder {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("OK"){
                    dialog, _ ->
                run {
                    dialogBtnClick.positiveBtnClick()
                    dialog.dismiss()
                }
            }
            builder.setNegativeButton("Batal"){
                dialog, which ->
                run {
                    dialogBtnClick.negativeBtnClick()
                    dialog.dismiss()
                }
            }
            return builder
        }

        fun showPromptDialog(context: Context, title: String, message: String, view: View, dialogBtnClick: OnCallbackPromptDialog): AlertDialog.Builder {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setView(view)
            builder.setPositiveButton("OK"){
                    dialog, _ ->
                run {
                    dialogBtnClick.positiveBtnClick(view)
                    dialog.dismiss()
                }
            }
            builder.setNegativeButton("Batal"){
                    dialog, which ->
                run {
                    dialogBtnClick.negativeBtnClick()
                    dialog.dismiss()
                }
            }
            return builder
        }
    }

    interface OnCallbackAlertDialog{
        fun positiveBtnClick()
    }

    interface OnCallbackConfirmDialog{
        fun positiveBtnClick()
        fun negativeBtnClick()
    }

    interface OnCallbackPromptDialog{
        fun positiveBtnClick(view: View)
        fun negativeBtnClick()
    }
}