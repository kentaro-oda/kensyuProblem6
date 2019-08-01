package problem6.entity;

import java.sql.Date;

import org.seasar.extension.jdbc.name.PropertyName;

import problem6.entity.OmikujiNames._OmikujiNames;

/**
 *ResultエンティティのNamesクラス
 *
 * @author k_oda
 *
 */
public class ResultNames {

	/**
	 * fortuneDayのプロパティ名を返却するメソッド
	 *
	 * @return	new PropertyName<Date>("fortune_day")/fortuneDayのプロパティ名
	 */
	public static PropertyName<Date> fortuneDay(){
		return new PropertyName<Date>("fortuneDay");
	}

	/**
	 * birthdayのプロパティ名を返却するメソッド
	 *
	 * @return	new PropertyName<Date>("birthday")/birthdayのプロパティ名
	 */
	public static PropertyName<Date> birthday(){
		return new PropertyName<Date>("birthday");
	}

	/**
	 * omikujiIdのプロパティ名を返却するメソッド
	 *
	 * @return	new PropertyName<Integer>("omikuji_id")/omikujiIdのプロパティ名
	 */
	public static PropertyName<Integer> omikujiId(){
		return new PropertyName<Integer>("omikujiId");
	}

	/**
	 * omikujiのプロパティ名を返却するメソッド
	 *
	 * @return	new _OmikujiNames("omikuji")/omikujiのプロパティ名
	 */
	public static _OmikujiNames omikuji() {
		return new _OmikujiNames("omikuji");
	}

	/**
	 *インスタンス構築とプロパティ名返却をするクラス
	 *
	 * @author k_oda
	 *
	 */
	public static class _ResultNames extends PropertyName<Result>{

		/**
		 *インスタンスを構築
		 */
		public _ResultNames() {

		}

		/**
		 *インスタンスを構築
		 *
		 * @param name	名前
		 */
		public _ResultNames(final String name) {

		}

		/**
		 *インスタンスを構築
		 *
		 * @param parent	親
		 * @param name		名前
		 */
		public _ResultNames(final PropertyName<?> parent, final String name) {

		}

		/**
		 * fortuneDayのプロパティ名を返却するメソッド
		 *
		 * @return	new PropertyName<Date>("fortune_day")/fortuneDayのプロパティ名
		 */
		public PropertyName<Date> fortuneDay(){
			return new PropertyName<Date>(this, "fortuneDay");
		}

		/**
		 * birthdayのプロパティ名を返却するメソッド
		 *
		 * @return	new PropertyName<Date>("birthday")/birthdayのプロパティ名
		 */
		public PropertyName<Date> birthday(){
			return new PropertyName<Date>(this, "birthday");
		}

		/**
		 * omikujiIdのプロパティ名を返却するメソッド
		 *
		 * @return	new PropertyName<Integer>("omikuji_id")/omikujiIdのプロパティ名
		 */
		public PropertyName<Integer> omikujiId(){
			return new PropertyName<Integer>(this, "omikujiId");
		}

		/**
		 * omikujiのプロパティ名を返却するメソッド
		 *
		 * @return	new _OmikujiNames("omikuji")/omikujiのプロパティ名
		 */
		public _OmikujiNames omikuji() {
			return new _OmikujiNames(this, "omikuji");
		}
	}
}
