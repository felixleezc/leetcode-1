package com.chuanlong.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chuanlong.leetcode.bean.Interval;

public class M056_MergeIntervals {


    public static void main(String[] args) {

        M056_MergeIntervals obj = new M056_MergeIntervals();
        
        List<Interval> intervals1 = new ArrayList<>();
        intervals1.add(new Interval(1, 3));
        intervals1.add(new Interval(2, 6));
        intervals1.add(new Interval(8, 10));
        intervals1.add(new Interval(15, 18));
        
        obj.merge(intervals1);
        
        
    }
    
    /**
     * For example,
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     * */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return intervals;
        }
        
        List<Point> points = new ArrayList<Point>();
        for(int i=0; i<intervals.size(); i++) {
            points.add(new Point(intervals.get(i).start, Point.START));
            points.add(new Point(intervals.get(i).end, Point.END));
        }
        Collections.sort(points);
        List<Interval> mergeList = new ArrayList<Interval>();
        int start = -1;
        int count = 0;
        for(int i=0; i<points.size(); i++) {
            Point point = points.get(i);
            if(point.position == Point.START) {
                if(start == -1) start = point.point;
                count++;
            } else {
                count--;
                if(count == 0) {
                    mergeList.add(new Interval(start, point.point));
                    start = -1;
                }
            }
        }
        
        return mergeList;
    }
    
    public static class Point implements Comparable<Point> {
        public final static int START = 1;
        public final static int END = 2;
        
        public int point;
        public int position; // 1 start, 2 end;
        
        public Point(int point, int position) {
            this.point = point;
            this.position = position;
        }
        
        @Override
        public int compareTo(Point p) {
            if (p == null) {
                return 1;
            }
            
            if (point < p.point) {
                return -1;
            } else if (point > p.point) {
                return 1;
            } else {
                if (position < p.position) {
                    return -1;
                } else if (position > p.position) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

}
