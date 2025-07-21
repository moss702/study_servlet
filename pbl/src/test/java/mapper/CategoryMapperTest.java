package mapper;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Category;
import lombok.extern.slf4j.Slf4j;
import util.MyBatisUtil;

@Slf4j
public class CategoryMapperTest {

	private CategoryMapper categoryMapper = MyBatisUtil.getsqlSession().getMapper(CategoryMapper.class);

	@Test
	@DisplayName("목록 조회")
	public void testlist() {
		List<Category> list = categoryMapper.list();
		list.forEach(b -> log.info("{}", b));
	}	
}


