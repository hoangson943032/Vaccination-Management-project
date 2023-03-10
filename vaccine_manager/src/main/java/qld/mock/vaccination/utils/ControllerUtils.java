package qld.mock.vaccination.utils;

import java.util.Arrays;

import org.springframework.data.domain.Page;






public class ControllerUtils {
	

	public static int[] merge(int[]... intarrays) {
	    return Arrays.stream(intarrays).flatMapToInt(Arrays::stream)
	            .toArray();
	}
	
	public static <T> int[] GenderPage(Page<T> page) {
		int[] body;
	    if (page.getTotalPages() > 7) {
	        int totalPages = page.getTotalPages();
	        int pageNumber = page.getNumber()+1;
	        int[] head = (pageNumber > 4) ? new int[]{1, -1} : new int[]{1,2,3};
	        int[] bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1) ? new int[]{pageNumber-2, pageNumber-1} : new int[]{};
	        int[] bodyCenter = (pageNumber > 3 && pageNumber < totalPages - 2) ? new int[]{pageNumber} : new int[]{};
	        int[] bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3) ? new int[]{pageNumber+1, pageNumber+2} : new int[]{};
	        int[] tail = (pageNumber < totalPages - 3) ? new int[]{-1, totalPages} : new int[] {totalPages-2, totalPages-1, totalPages};
	        body = merge(head, bodyBefore, bodyCenter, bodyAfter, tail);

	    } else {
	        body = new int[page.getTotalPages()];
	        for (int i = 0; i < page.getTotalPages(); i++) {
	            body[i] = 1+i;
	        }
	    }
		return body;
	}

}
