package com.fabriciooliveira.projectstone.Util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by fabriciooliveira on 12/25/15.
 */
public class MensagemUtil {

    public static void addMsg(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public static void addMsgOK(Activity activity, String titulo, String msg, int icone) {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(activity);
        builderDialog.setTitle(titulo);
        builderDialog.setIcon(icone);
        builderDialog.setMessage(msg);
        builderDialog.setNeutralButton("Ok", null);
        builderDialog.show();
    }

    public static void addMsgConfirm(Activity activity, String titulo, String msg, int icone, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(activity);
        builderDialog.setTitle(titulo);
        builderDialog.setIcon(icone);
        builderDialog.setMessage(msg);
        builderDialog.setPositiveButton("Sim", listener);
        builderDialog.setNegativeButton("Nao", null);
        builderDialog.show();
    }


}
