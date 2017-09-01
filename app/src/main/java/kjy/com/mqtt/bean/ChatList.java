package kjy.com.mqtt.bean;



public class ChatList {
    private String Types;
    private String MultipleOptions;
    private String Content;
    private String Head;
    private String Images;
    private String Time;
    private float Voice;
    private String FilePath;
    private String Player;
    public static class Type{
        public static final String SendOut = "SendOut";
        public static final String Receive = "Receive";
        public static final String Text = "Text";
        public static final String Image = "Image";
        public static final String Voice = "Voice";
        public static final String Player = "Player";
    }
    private ChatList(Builder builder){
        this.Types = builder.Types;
        this.MultipleOptions = builder.MultipleOptions;
        this.Content = builder.Content;
        this.Head = builder.Head;
        this.Images = builder.Images;
        this.Time = builder.Time;
        this.Voice = builder.Voice;
        this.Player = builder.Player;
        this.FilePath = builder.FilePath;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public float getVoice() {
        return Voice;
    }

    public void setVoice(float voice) {
        Voice = voice;
    }

    public String getPlayer() {
        return Player;
    }

    public void setPlayer(String player) {
        Player = player;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMultipleOptions() {
        return MultipleOptions;
    }

    public void setMultipleOptions(String MultipleOptions) {
        MultipleOptions = MultipleOptions;
    }

    public String getTypes() {
        return Types;
    }

    public void setTypes(String types) {
        Types = types;
    }

    public static class Builder{
        private String MultipleOptions;
        private String Types;
        private String Content;
        private String Head;
        private String Images;
        private String Time;
        private float Voice;
        private String FilePath;
        private String Player;
        public Builder MultipleOptions(String MultipleOptions){
            this.MultipleOptions = MultipleOptions;
            return this;
        }
        public Builder Types(String Types){
            this.Types = Types;
            return this;
        }
        public Builder Content(String content){
            this.Content = content;
            return this;
        }
        public Builder Head(String Head){
            this.Head = Head;
            return this;
        }
        public Builder Images(String Images){
            this.Images = Images;
            return this;
        }
        public Builder Time(String Time){
            this.Time = Time;
            return this;
        }
        public Builder Voice(float Voice){
            this.Voice = Voice;
            return this;
        }
        public Builder FilePath(String FilePath){
            this.FilePath = FilePath;
            return this;
        }
        public Builder Player(String Player){
            this.Player = Player;
            return this;
        }
        public ChatList build(){
            return new ChatList(this);
        }
    }
}
