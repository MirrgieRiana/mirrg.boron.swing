package mirrg.boron.swing;

import java.awt.Color;

public class UtilsColor
{

	//////////////////////// IO

	/**
	 * 各値は小数点以下を四捨五入されたあと、0～255の範囲に丸められて使用されます。
	 */
	public static int fromDoublesRGBA(double r, double g, double b, double a)
	{
		return fromIntegersRGBA((int) Math.round(r), (int) Math.round(g), (int) Math.round(b), (int) Math.round(a));
	}

	/**
	 * 各値は小数点以下を四捨五入されたあと、0～255の範囲に丸められて使用されます。
	 */
	public static int fromDoublesRGB(double r, double g, double b)
	{
		return fromIntegersRGB((int) Math.round(r), (int) Math.round(g), (int) Math.round(b));
	}

	/**
	 * 各値は0～255の範囲に丸められて使用されます。
	 */
	public static int fromIntegersRGBA(int r, int g, int b, int a)
	{
		if (r < 0) r = 0;
		if (g < 0) g = 0;
		if (b < 0) b = 0;
		if (a < 0) a = 0;
		if (r >= 256) r = 255;
		if (g >= 256) g = 255;
		if (b >= 256) b = 255;
		if (a >= 256) a = 255;
		return (a << 24) | (r << 16) | (g << 8) | b;
	}

	/**
	 * 各値は0～255の範囲に丸められて使用されます。
	 */
	public static int fromIntegersRGB(int r, int g, int b)
	{
		if (r < 0) r = 0;
		if (g < 0) g = 0;
		if (b < 0) b = 0;
		if (r >= 256) r = 255;
		if (g >= 256) g = 255;
		if (b >= 256) b = 255;
		return (r << 16) | (g << 8) | b;
	}

	//

	public static int fromIntegerArrayRGBA(int[] rgba)
	{
		return fromIntegersRGBA(rgba[0], rgba[1], rgba[2], rgba[3]);
	}

	public static int fromIntegerArrayRGB(int[] rgba)
	{
		return fromIntegersRGB(rgba[0], rgba[1], rgba[2]);
	}

	public static void toIntegerArrayRGBA(int[] destRgba, int argb)
	{
		destRgba[3] = (argb >> 24) & 0xff;
		destRgba[0] = (argb >> 16) & 0xff;
		destRgba[1] = (argb >> 8) & 0xff;
		destRgba[2] = (argb >> 0) & 0xff;
	}

	public static int[] toIntegerArrayRGBA(int argb)
	{
		int[] arrayRgba = new int[4];
		toIntegerArrayRGBA(arrayRgba, argb);
		return arrayRgba;
	}

	public static void toIntegerArrayRGB(int[] destRgb, int rgb)
	{
		destRgb[0] = (rgb >> 16) & 0xff;
		destRgb[1] = (rgb >> 8) & 0xff;
		destRgb[2] = (rgb >> 0) & 0xff;
	}

	public static int[] toIntegerArrayRGB(int rgb)
	{
		int[] arrayRgb = new int[3];
		toIntegerArrayRGB(arrayRgb, rgb);
		return arrayRgb;
	}

	//

	public static int fromColorRGBA(Color colorRgba)
	{
		return colorRgba.getRGB();
	}

	public static int fromColorRGB(Color colorRgb)
	{
		return colorRgb.getRGB() & 0xffffff;
	}

	public static Color toColorRGBA(int argb)
	{
		return new Color(argb, true);
	}

	public static Color toColorRGB(int rgb)
	{
		return new Color(rgb, false);
	}

	//////////////////////// conversion

	// Random Color

	public static int randomRGBA(int minR, int maxR, int minG, int maxG, int minB, int maxB, int minA, int maxA)
	{
		return fromIntegersRGBA(
			(int) (Math.random() * (maxR - minR + 1) + minR),
			(int) (Math.random() * (maxG - minG + 1) + minG),
			(int) (Math.random() * (maxB - minB + 1) + minB),
			(int) (Math.random() * (maxA - minA + 1) + minA));
	}

	public static int randomRGB(int minR, int maxR, int minG, int maxG, int minB, int maxB)
	{
		return fromIntegersRGB(
			(int) (Math.random() * (maxR - minR + 1) + minR),
			(int) (Math.random() * (maxG - minG + 1) + minG),
			(int) (Math.random() * (maxB - minB + 1) + minB));
	}

	/**
	 * アルファ成分は不透明で固定です。
	 */
	public static int randomRGB(int min, int max)
	{
		return randomRGB(min, max, min, max, min, max);
	}

	/**
	 * 完全に不透明で各成分が0以上255以下のランダムな色を返します。
	 */
	public static int randomRGB()
	{
		return randomRGB(0, 255);
	}

	/**
	 * 完全に不透明で各成分が0以上127以下のランダムな色を返します。
	 */
	public static int randomRGBDark()
	{
		return randomRGB(0, 127);
	}

	/**
	 * 完全に不透明で各成分が128以上255以下のランダムな色を返します。
	 */
	public static int randomRGBLight()
	{
		return randomRGB(128, 255);
	}

	/**
	 * 色a, bの中間色を計算します。
	 * ratioが0以下の場合はaを返し、ratioが1以上の場合はbを返します。
	 * ratioが小数の場合は、a, bを適切な比率で混合した色を計算して返します。
	 */
	public static int ratio(double ratio, int a, int b)
	{
		if (ratio <= 0) return a;
		if (ratio >= 1) return b;

		int[] a2 = new int[4];
		int[] b2 = new int[4];

		toIntegerArrayRGBA(a2, a);
		toIntegerArrayRGBA(b2, b);

		return fromIntegersRGBA(
			(int) ((1 - ratio) * a2[0] + ratio * b2[0]),
			(int) ((1 - ratio) * a2[1] + ratio * b2[1]),
			(int) ((1 - ratio) * a2[2] + ratio * b2[2]),
			(int) ((1 - ratio) * a2[3] + ratio * b2[3]));
	}

}
