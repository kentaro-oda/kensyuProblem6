package problem6.entity;

import java.sql.Date;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * CommonエンティティのNamesクラス
 *
 * @author k_oda
 *
 */
public class CommonNames {

	/**
	 * updaterのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<String>("updater")/updaterのプロパティ名
	 */
	public static PropertyName<String> updater(){
		return new PropertyName<String>("updater");
	}

	/**
	 * updateDayのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<Date>("update_day")/updateDayのプロパティ名
	 */
	public static PropertyName<Date> updateDay(){
		return new PropertyName<Date>("updateDay");
	}

	/**
	 * createrのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<String>("creater")/createrのプロパティ名
	 */
	public static PropertyName<String> creater(){
		return new PropertyName<String>("creater");
	}

	/**
	 * createDayのプロパティ名を返すメソッド
	 *
	 * @return	new PropertyName<Date>("create_day")/createDayのプロパティ名
	 */
	public static PropertyName<Date> createDay(){
		return new PropertyName<Date>("createDay");
	}

	/**
	 * インスタンス構築とプロパティ名を返却のクラス
	 *
	 * @author k_oda
	 */
	public static class _CommonNames extends PropertyName<Common>{

		/**
		 * インスタンスを構築します
		 */
		public _CommonNames() {
		}

		/**
		 * インスタンスを構築します
		 *
		 * @param name		名前
		 */
		public _CommonNames(final String name) {
			super(name);
		}

		/**
		 * インスタンスを構築します
		 *
		 * @param parent	親
		 * @param name		名前
		 */
		public _CommonNames(final PropertyName<?> parent, final String name) {
			super(parent, name);
		}

		/**
		 * updaterのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<String>("updater")/updaterのプロパティ名
		 */
		public PropertyName<String> updater(){
			return new PropertyName<String>(this, "updater");
		}

		/**
		 * updateDayのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<Date>("update_day")/updateDayのプロパティ名
		 */
		public PropertyName<Date> updateDay(){
			return new PropertyName<Date>(this, "updateDay");
		}


		/**
		 * createrのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<String>("creater")/createrのプロパティ名
		 */
		public PropertyName<String> creater(){
			return new PropertyName<String>(this, "creater");
		}

		/**
		 * createDayのプロパティ名を返すメソッド
		 *
		 * @return	new PropertyName<Date>("create_day")/createDayのプロパティ名
		 */
		public PropertyName<Date> createDay(){
			return new PropertyName<Date>(this, "createDay");
		}
	}

}
