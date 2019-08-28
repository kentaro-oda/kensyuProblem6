package problem6.entity;

import org.seasar.extension.jdbc.name.PropertyName;

import problem6.entity.OmikujiNames._OmikujiNames;

/**
 * FortuneエンティティのNamesクラス
 *
 * @author k_oda
 *
 */
public class FortuneNames {

	/**
	 * fortuneIdのプロパティ名を返すメソッド
	 *
	 * @return	fortuneIdのプロパティ名
	 */
	public static PropertyName<Integer> fortuneId(){
		return new PropertyName<Integer>("fortuneId");
	}

	/**
	 * fortuneNameのプロパティ名を返すメソッド
	 *
	 * @return	fortuneNameのプロパティ名
	 */
	public static PropertyName<String> fortuneName(){
		return new PropertyName<String>("fortuneName");
	}

	/**
	 * omikujiListのプロパティ名を返すメソッド
	 *
	 * @return	omikujiListのプロパティ名
	 */
	public static _OmikujiNames omikujiList(){
		return new _OmikujiNames("omikujiList");
	}
	/**
	 * インスタンス構築とプロパティ名返却のクラス
	 *
	 * @author k_oda
	 */
	public static class _FortuneNames extends PropertyName<Fortune>{

		/**
		 * インスタンスを構築
		 */
		public _FortuneNames() {

		}

		/**
		 * インスタンスを構築
		 *
		 * @param name	名前
		 */
		public _FortuneNames(final String name) {
			super(name);
		}

		/**
		 * インスタンスを構築
		 *
		 * @param parent	親
		 * @param name		名前
		 */
		public _FortuneNames(final PropertyName<?> parent, final String name) {
			super(parent, name);
		}

		/**
		 * fortuneIdのプロパティ名を返すメソッド
		 *
		 * @return	fortuneIdのプロパティ名
		 */
		public PropertyName<Integer> fortuneId(){
			return new PropertyName<Integer>(this, "fortuneId");
		}

		/**
		 * fortuneNameのプロパティ名を返すメソッド
		 *
		 * @return	fortuneNameのプロパティ名
		 */
		public PropertyName<String> fortuneName(){
			return new PropertyName<String>(this, "fortuneName");
		}

		/**
		 * omikujiListのプロパティ名を返すメソッド
		 *
		 * @return	omikujiListのプロパティ名
		 */
		public _OmikujiNames omikujiList(){
			return new _OmikujiNames(this, "omikujiList");
		}
	}
}
