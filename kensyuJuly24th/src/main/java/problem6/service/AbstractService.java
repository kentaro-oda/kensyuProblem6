package problem6.service;

import org.seasar.extension.jdbc.service.S2AbstractService;

/**
 * デフォルトで存在するS2Abstractserviceのメソッドを使うための抽象クラス
 * S2AbstractServiceを継承
 * @author k_oda
 *
 * @param <ENTITY>	各サービスクラスに対応したエンティティを指定
 */
public abstract class AbstractService<ENTITY> extends S2AbstractService<ENTITY> {

}
