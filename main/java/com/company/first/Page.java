package com.company.first;

public class Page {
    int count; //총 게시물 수
    int postNum = 5; //한페이지에 출력할 게시물 개수
    int pageNum;
    int displayPost;
    int pageNum_cnt;
    int endPageNum;
    int startPageNum;
    int endPageNum_tmp;
    boolean prev;
    boolean next;

    void set(int num, int size){
        this.count=size;
        this.pageNum = (int)Math.ceil((double)count/postNum);
        this.displayPost = (num-1)*postNum;
        this.pageNum_cnt = 5;
        this.endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
        this.startPageNum = endPageNum - (pageNum_cnt - 1);
        this.endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));

        if(endPageNum > endPageNum_tmp) {
            endPageNum = endPageNum_tmp;
        }
        this.prev = startPageNum == 1 ? false : true;
        this.next = endPageNum * pageNum_cnt >= count ? false : true;
    }
}
