//package com.example.demo.restController;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.ex01.domain.dto.MemoDto;
//import com.example.ex01.domain.service.MemoServiceImpl;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@RequestMapping("/memo")
//@Slf4j
//public class MemoRestController {
//
//	@Autowired
//	private MemoServiceImpl memoServiceImpl;
//
//	@GetMapping("/add_get")
//	public ResponseEntity<String> add_get(MemoDto memoDto) throws Exception {
//		log.info("GET /memo/add_get..." + memoDto);
//		memoServiceImpl.memoAddTx(memoDto);
//
//		return new ResponseEntity<>("{\"success\":\"insert_ok\"}",HttpStatus.OK);
//
//	}
//
//	@PostMapping(value="/add_post",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void add_post(@RequestBody MemoDto memoDto) throws Exception {
//		log.info("POST /memo/add_get..." + memoDto);
//		memoServiceImpl.memoAddTx(memoDto);
//	}
//
//	@GetMapping(value="/getMemo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity< List<MemoDto> > getMemo() {
//		log.info("GET /memo/add_get...");
//		return new ResponseEntity( memoServiceImpl.getMemos()  ,HttpStatus.OK);
//	}
//
//	@PutMapping(value="/put",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Boolean> put(@RequestBody MemoDto memoDto) {
//		log.info("PUT /memo/put..." + memoDto);
//
//		boolean isUpdate = 	 memoServiceImpl.memoUpdate(memoDto);
//
//		return isUpdate? new ResponseEntity<>(isUpdate,HttpStatus.OK) : new ResponseEntity<>(isUpdate,HttpStatus.BAD_GATEWAY);
//
//	}
//
//	@PatchMapping(value = "/patch",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Boolean> update_patch(@RequestBody MemoDto memoDto) {
//		log.info("PATCH /memo/patch.." + memoDto);
//		boolean isUpdate = 	 memoServiceImpl.memoUpdate_patch(memoDto);
//		return isUpdate? new ResponseEntity<>(isUpdate,HttpStatus.OK) : new ResponseEntity<>(isUpdate,HttpStatus.BAD_GATEWAY);
//	}
//
//	@DeleteMapping("/delete")
//	public ResponseEntity<Boolean> delete(int id) {
//		log.info("DELETE /memo/delete.." + id);
//		boolean isDelete = 	 memoServiceImpl.memoDelete(id);
//		return isDelete? new ResponseEntity<>(isDelete,HttpStatus.OK) : new ResponseEntity<>(isDelete,HttpStatus.BAD_GATEWAY);
//	}
//
//
//
//}
//
//
//
//
//
