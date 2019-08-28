package problem6.entity;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * AddressエンティティのNamesクラス
 * @author k_oda
 *
 */
public class PostalNames {

	/**
	 * idのプロパティ名を返すメソッド
	 *
	 * @return	idのプロパティ名
	 */
	public static PropertyName<Integer> id(){
		return new PropertyName<Integer>("id");
	}

	/**
	 * postalCodeのプロパティ名を返すメソッド
	 *
	 * @return	postalCodeのプロパティ名
	 */
	public static PropertyName<String> postalCode(){
		return new PropertyName<String>("postalCode");
	}

	/**
	 * prefectureのプロパティ名を返すメソッド
	 *
	 * @return	prefectureのプロパティ名
	 */
	public static PropertyName<String> prefecture(){
		return new PropertyName<String>("prefecture");
	}

	/**
	 * cityのプロパティ名を返すメソッド
	 *
	 * @return	cityのプロパティ名
	 */
	public static PropertyName<String> city(){
		return new PropertyName<String>("city");
	}

	/**
	 * addressのプロパティ名を返すメソッド
	 *
	 * @return	addressのプロパティ名
	 */
	public static PropertyName<String> addressDetail(){
		return new PropertyName<String>("addressDetail");
	}

	/**
	 * cityEscaperのプロパティ名を返すメソッド
	 *
	 * @return	cityEscaperのプロパティ名
	 */
	public static PropertyName<String> cityEscaper(){
		return new PropertyName<String>("cityEscaper");
	}

	/**
	 * addressDetailEscaperのプロパティ名を返すメソッド
	 *
	 * @return	addressDetailEscaperのプロパティ名
	 */
	public static PropertyName<String> addressDetailEscaper(){
		return new PropertyName<String>("addressDetailEscaper");
	}

	/**
	 * インスタンス構築とプロパティ名を返却のクラス
	 *
	 * @author k_oda
	 */
	public static class _AddressNames extends PropertyName<Postal>{

		/**
		 * インスタンスを構築します
		 */
		public _AddressNames() {

		}

		/**
		 * インスタンスを構築します
		 *
		 * @param name		名前
		 */
		public _AddressNames(final String name) {
			super(name);
		}

		/**
		 * インスタンスを構築します
		 *
		 * @param parent	親
		 * @param name		名前
		 */
		public _AddressNames(final PropertyName<?> parent, final String name) {
			super(parent, name);
		}

		/**
		 * idのプロパティ名を返すメソッド
		 *
		 * @return	idのプロパティ名
		 */
		public PropertyName<Integer> id(){
			return new PropertyName<Integer>(this, "id");
		}

		/**
		 * postalCodeのプロパティ名を返すメソッド
		 *
		 * @return	postalCodeのプロパティ名
		 */
		public PropertyName<String> postalCode(){
			return new PropertyName<String>(this, "postalCode");
		}

		/**
		 * prefectureのプロパティ名を返すメソッド
		 *
		 * @return	prefectureのプロパティ名
		 */
		public PropertyName<String> prefecture(){
			return new PropertyName<String>(this, "prefecture");
		}

		/**
		 * cityのプロパティ名を返すメソッド
		 *
		 * @return	cityのプロパティ名
		 */
		public PropertyName<String> city(){
			return new PropertyName<String>(this, "city");
		}

		/**
		 * addressのプロパティ名を返すメソッド
		 *
		 * @return	addressのプロパティ名
		 */
		public PropertyName<String> addressDetail(){
			return new PropertyName<String>(this, "addressDetail");
		}

		/**
		 * cityEscaperのプロパティ名を返すメソッド
		 *
		 * @return	cityEscaperのプロパティ名
		 */
		public PropertyName<String> cityEscaper(){
			return new PropertyName<String>(this, "cityEscaper");
		}

		/**
		 * addressDetailEscaperのプロパティ名を返すメソッド
		 *
		 * @return	addressDetailEscaperのプロパティ名
		 */
		public PropertyName<String> addressDetailEscaper(){
			return new PropertyName<String>(this, "addressDetailEscaper");
		}
	}
}
