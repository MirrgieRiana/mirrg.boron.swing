package mirrg.boron.swing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * このメソッドはAWTイベントディスパッチャースレッド以外のスレッドから呼び出してもかまいません。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface SwingThreadSafe
{

}
