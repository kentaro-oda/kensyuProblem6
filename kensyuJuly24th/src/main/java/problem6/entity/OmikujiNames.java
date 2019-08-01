package problem6.entity;

import org.seasar.extension.jdbc.name.PropertyName;

import problem6.entity.FortuneNames._FortuneNames;
import problem6.entity.ResultNames._ResultNames;

/**
 * OmikujiエンティティのNamesクラス
 *
 * @author k_oda
 *
 */
public class OmikujiNames {

	/**
	 * omikujiIdのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<Integer>("omikuji_id")/omikujiIdのプロパティ名
	 */
	public static PropertyName<Integer> omikujiId(){
		return new PropertyName<Integer>("omikujiId");
	}

	/**
	 * fortuneIdのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<Integer>(this, "fortune_id")/fortuneIdのプロパティ名
	 */
	public static PropertyName<Integer> fortuneId(){
		return new PropertyName<Integer>("fortuneId");
	}

	/**
	 * negaigotoのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<String>("negaigoto")/negaigotoのプロパティ名
	 */
	public static PropertyName<String> negaigoto(){
		return new PropertyName<String>("negaigoto");
	}

	/**
	 * akinaiのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<String>("akinai")/akinaiのプロパティ名
	 */
	public static PropertyName<String> akinai(){
		return new PropertyName<String>("akinai");
	}

	/**
	 * gakumonのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<String>("gakumon")/gakumonのプロパティ名
	 */
	public static PropertyName<String> gakumon(){
		return new PropertyName<String>("gakumon");
	}

	/**
	 * resultListのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<List<Result>>("result")/resultListのプロパティ名
	 */
	public static _ResultNames resultList(){
		return new _ResultNames("resultList");
	}

	/**
	 * fortuneのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<Fortune>("fortune")/fortuneのプロパティ名
	 */
	public static _FortuneNames fortune(){
		return new _FortuneNames("fortune");
	}

	/**
	 * インスタンス構築とプロパティ名返却のクラス
	 *
	 * @author k_oda
	 */
	public static class _OmikujiNames extends PropertyName<Omikuji>{

		/**
		 * インスタンスの構築
		 */
		public _OmikujiNames(){

		}

		/**
		 * インスタンスの構築
		 *
		 * @param name	名前
		 */
		public _OmikujiNames(final String name){
			super(name);
		}

		/**
		 * インスタンスの構築
		 *
		 * @param parent	親
		 * @param name		名前
		 */
		public _OmikujiNames(final PropertyName<?> parent, final String name){
			super(parent, name);
		}

		/**
		 * omikujiIdのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<Integer>("omikuji_id")/omikujiIdのプロパティ名
		 */
		public PropertyName<Integer> omikujiId(){
			return new PropertyName<Integer>(this, "omikujiId");
		}

		/**
		 * fortuneIdのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<Integer>(this, "fortune_id")/fortuneIdのプロパティ名
		 */
		public PropertyName<Integer> fortuneId(){
			return new PropertyName<Integer>(this, "fortuneId");
		}

		/**
		 * negaigotoのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<String>("negaigoto")/negaigotoのプロパティ名
		 */
		public PropertyName<String> negaigoto(){
			return new PropertyName<String>(this, "negaigoto");
		}

		/**
		 * akinaiのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<String>("akinai")/akinaiのプロパティ名
		 */
		public PropertyName<String> akinai(){
			return new PropertyName<String>(this, "akinai");
		}

		/**
		 * gakumonのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<String>("gakumon")/gakumonのプロパティ名
		 */
		public PropertyName<String> gakumon(){
			return new PropertyName<String>(this, "gakumon");
		}

		/**
		 * resultListのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<List<Result>>("result")/resultListのプロパティ名
		 */
		public _ResultNames resultList(){
			return new _ResultNames(this, "resultList");
		}

		/**
		 * fortuneのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<Fortune>("fortune")/fortuneのプロパティ名
		 */
		public _FortuneNames fortune(){
			return new _FortuneNames(this, "fortune");
		}
	}
}
