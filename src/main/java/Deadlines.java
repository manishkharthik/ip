// Source code is decompiled from a .class file using FernFlower decompiler.
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
   private final LocalDateTime by;
   private static final DateTimeFormatter PRETTY_DATE = DateTimeFormatter.ofPattern("MMM dd uuuu");

   public Deadlines(String var1, LocalDateTime var2) {
      super(var1);
      this.by = var2;
   }

   public LocalDateTime getBy() {
      return this.by;
   }

   public static Deadlines parser(String var0) throws EmptyDescriptionException, TaskFormattingException {
      if (!var0.contains("/by")) {
         throw new TaskFormattingException("Invalid deadline format. Make sure to include /by");
      } else {
         String[] var1 = var0.substring(8).split("/by", 2);
         String var2 = var1[0].trim();
         String var3 = var1[1].trim();
         if (var2.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty.");
         } else if (var3.isEmpty()) {
            throw new EmptyDescriptionException("Deadline cannot be empty.");
         } else {
            return new Deadlines(var2, DateTimeParser.parseFlexible(var3));
         }
      }
   }

   public String toString() {
      String var10000 = this.getIsComplete() ? "[X] " : "[ ] ";
      return "[D]" + var10000 + super.toString() + " (by: " + DateTimeParser.formatPretty(this.by) + ")";
   }

   public String serialize() {
      String var10000 = this.getIsComplete() ? "1" : "0";
      return "D | " + var10000 + " | " + this.getDescription() + " | " + String.valueOf(this.by);
   }

   public static Deadlines fromData(String[] var0) {
      boolean var1 = var0[1].trim().equals("1");
      String var2 = var0[2].trim();
      LocalDateTime var3 = LocalDateTime.parse(var0[3].trim());
      Deadlines var4 = new Deadlines(var2, var3);
      if (var1) {
         var4.markAsComplete();
      }

      return var4;
   }
}