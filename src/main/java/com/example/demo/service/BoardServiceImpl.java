package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public Long insert(BoardDTO boardDTO) {
        // save() : 저장
        // 저장하는 객체는 Entity (Board)
        // DTO => Entity 로 변환
        Board board = convertDtoToEntity(boardDTO); // 변환 후
        Long bno = boardRepository.save(board).getBno(); // 저장

        return bno;
    }


//    @Override
//    public List<BoardDTO> getList() {
//        /* DB에서 가져오는 return List<Board> => List<BoardDTO> 로 변환
//        * findAll() => 전체 값 리턴
//        * select * from board order by bno desc
//        * 정렬 : Sort.by(Sort.direction.DESC, "정렬기준 칼럼명")
//        * */
//        List<Board> boardList = boardRepository.findAll(
//                Sort.by(Sort.Direction.DESC,"bno"));
//
//        List<BoardDTO> boardDTOList = boardList
//                    .stream()
//                    .map(board -> convertEntityToDto(board))
//                    .toList();
//        return boardDTOList;
//    }

    @Override
    public Page<BoardDTO> getList(int pageNo) {
        // limit 시작번지, 개수 => 번지는 0부터 시작
        // pageNo = 1 -> limit 0, 10
        Pageable pageable = PageRequest.of(pageNo -1, 10, Sort.by("bno").descending());
        Page<Board> pageList =  boardRepository.findAll(pageable); 
        Page<BoardDTO> boardDTOPage = pageList.map(this :: convertEntityToDto);

        return boardDTOPage;
    }

    @Override
    public BoardDTO getDetail(long bno) {
        /* findOne 기본키를 이용하여 원하는 객체 검색 where ~
        * findby칼럼명 => 원하는 칼럼명을 이용하여 검색
        * findById => findOne
        * Optional<T> : NPE(NullPointException)가 발생하지 않도록 도와줌
        * Optional.isEmpty() : null일 경우 true / false
        * Optional.isPresent() : 값이 있는지를 확인 true / false
        * Optional.get() : 객체 가져오기
        * */

        Optional<Board> optional = boardRepository.findById(bno);
        if(optional.isPresent()){
            Board board = optional.get();
            boardReadCountUpdate(board, 1);

            BoardDTO boardDTO = convertEntityToDto(board);
            return boardDTO;
        }
        return null;
    }
    private void boardReadCountUpdate(Board board, int i){
        // readCount update
        board.setReadCount(board.getReadCount()+i);
        boardRepository.save(board);
    }
}
