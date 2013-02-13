package br.com.hachitecnologia.devolvame.util;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class Util {

	/**
	 * Converte um objeto do tipo Bitmap para byte[] (array de bytes)
	 * 
	 * @param imagem
	 * @param qualidadeDaImagem
	 * @return
	 */
	public static byte[] converteBitmapPraByteArray(Bitmap imagem,
			int qualidadeDaImagem) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		imagem.compress(CompressFormat.PNG, qualidadeDaImagem, stream);
		return stream.toByteArray();
	}

	/**
	 * Converte um objeto do tipo byte[] (array de bytes) para Bitmap
	 * 
	 * @param imagem
	 * @return
	 */
	public static Bitmap converteByteArrayPraBitmap(byte[] imagem) {
		return BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
	}

}
