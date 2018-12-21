package com.pengshengtj.comment;

import java.util.List;

public class JsonResult<T> {
   private List<T> cols;

   public JsonResult(List<T> cols) {
      this.cols = cols;
   }

   public List<T> getList() {
      return cols;
   }

   public void setList(List<T> cols) {
      this.cols = cols;
   }

   @Override
   public String toString() {
      return "JsonResult{" +
              "cols=" + cols +
              '}';
   }
}
