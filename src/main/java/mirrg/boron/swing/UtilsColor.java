package mirrg.boron.swing;

import java.awt.Color;

public class UtilsColor
{

	// Color creator

	/**
	 * 引数は0以上255以下の整数値に丸められます。
	 */
	public static Color createColor(double r, double g, double b, double a)
	{
		return createColor((int) r, (int) g, (int) b, (int) a);
	}

	/**
	 * 引数は0以上255以下の整数値に丸められます。
	 */
	public static Color createColor(double r, double g, double b)
	{
		return createColor((int) r, (int) g, (int) b);
	}

	/**
	 * 引数は0以上255以下の整数値に丸められます。
	 */
	public static Color createColor(int r, int g, int b, int a)
	{
		if (r < 0) r = 0;
		if (g < 0) g = 0;
		if (b < 0) b = 0;
		if (a < 0) a = 0;
		if (r >= 256) r = 255;
		if (g >= 256) g = 255;
		if (b >= 256) b = 255;
		if (a >= 256) a = 255;
		return new Color(r, g, b, a);
	}

	/**
	 * 引数は0以上255以下の整数値に丸められます。
	 */
	public static Color createColor(int r, int g, int b)
	{
		if (r < 0) r = 0;
		if (g < 0) g = 0;
		if (b < 0) b = 0;
		if (r >= 256) r = 255;
		if (g >= 256) g = 255;
		if (b >= 256) b = 255;
		return new Color(r, g, b);
	}

	// Random Color

	public static Color createColorRandom(int minR, int maxR, int minG, int maxG, int minB, int maxB, int minA, int maxA)
	{
		return createColor(
			(int) (Math.random() * (maxR - minR + 1) + minR),
			(int) (Math.random() * (maxG - minG + 1) + minG),
			(int) (Math.random() * (maxB - minB + 1) + minB),
			(int) (Math.random() * (maxA - minA + 1) + minA));
	}

	public static Color createColorRandom(int minR, int maxR, int minG, int maxG, int minB, int maxB)
	{
		return createColor(
			(int) (Math.random() * (maxR - minR + 1) + minR),
			(int) (Math.random() * (maxG - minG + 1) + minG),
			(int) (Math.random() * (maxB - minB + 1) + minB));
	}

	/**
	 * アルファ成分は不透明で固定です。
	 */
	public static Color createColorRandom(int min, int max)
	{
		return createColorRandom(min, max, min, max, min, max);
	}

	/**
	 * 完全に不透明で各成分が0以上255以下のランダムな色を返します。
	 */
	public static Color createColorRandom()
	{
		return createColorRandom(0, 255);
	}

	/**
	 * 完全に不透明で各成分が0以上127以下のランダムな色を返します。
	 */
	public static Color createColorRandomDark()
	{
		return createColorRandom(0, 127);
	}

	/**
	 * 完全に不透明で各成分が128以上255以下のランダムな色を返します。
	 */
	public static Color createColorRandomLight()
	{
		return createColorRandom(128, 255);
	}

	// int color

	public static int getARGB(int[] RGBA)
	{
		return getARGB(RGBA[0], RGBA[1], RGBA[2], RGBA[3]);
	}

	public static int getRGB(int[] RGB)
	{
		return getRGB(RGB[0], RGB[1], RGB[2]);
	}

	public static int getARGB(int r, int g, int b, int a)
	{
		return (a << 24) | (r << 16) | (g << 8) | b;
	}

	public static int getRGB(int r, int g, int b)
	{
		return (r << 16) | (g << 8) | b;
	}

	public static void getFromARGB(int[] destRGBA, int argb)
	{
		destRGBA[3] = (argb >> 24) & 0xff;
		destRGBA[0] = (argb >> 16) & 0xff;
		destRGBA[1] = (argb >> 8) & 0xff;
		destRGBA[2] = (argb >> 0) & 0xff;
	}

	public static void getFromRGB(int[] destRGB, int rgb)
	{
		destRGB[0] = (rgb >> 16) & 0xff;
		destRGB[1] = (rgb >> 8) & 0xff;
		destRGB[2] = (rgb >> 0) & 0xff;
	}

	public static int[] getFromARGB(int argb)
	{
		int[] RGBA = new int[4];
		getFromARGB(RGBA, argb);
		return RGBA;
	}

	public static int[] getFromRGB(int rgb)
	{
		int[] RGB = new int[4];
		getFromRGB(RGB, rgb);
		return RGB;
	}

	// Ratio Color

	/**
	 * 色a, bの中間色を計算します。
	 * ratioが0以下の場合はaを返し、ratioが1以上の場合はbを返します。
	 * ratioが小数の場合は、a, bを適切な比率で混合した色を計算して返します。
	 */
	public static int getARGBRatio(double ratio, int a, int b)
	{
		if (ratio <= 0) return a;
		if (ratio >= 1) return b;

		int[] a2 = new int[4];
		int[] b2 = new int[4];

		getFromARGB(a2, a);
		getFromARGB(b2, b);

		return getARGB(
			(int) ((1 - ratio) * a2[0] + ratio * b2[0]),
			(int) ((1 - ratio) * a2[1] + ratio * b2[1]),
			(int) ((1 - ratio) * a2[2] + ratio * b2[2]),
			(int) ((1 - ratio) * a2[3] + ratio * b2[3]));
	}

}
