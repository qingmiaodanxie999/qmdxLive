package com.phoenix.qingmiaodanxie.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class UpdateLive implements Serializable {
    /**
     * result : [{"created_at":1489649682684,"updated_at":1489649713052,"id":1167829828173889,"data":{"status":0,"live_type":0,"pic":"http://img4.imgtn.bdimg.com/it/u=3547796456,889162116&fm=23&gp=0.jpg","live_name":"2\n7\n"},"uid":1166262852976685}]
     * error_code : 0
     */

    private int error_code;
    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * created_at : 1489649682684
         * updated_at : 1489649713052
         * id : 1167829828173889
         * data : {"status":0,"live_type":0,"pic":"http://img4.imgtn.bdimg.com/it/u=3547796456,889162116&fm=23&gp=0.jpg","live_name":"2\n7\n"}
         * uid : 1166262852976685
         */

        private long created_at;
        private long updated_at;
        private long id;
        private DataBean data;
        private long uid;

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(long updated_at) {
            this.updated_at = updated_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public static class DataBean {
            /**
             * status : 0
             * live_type : 0
             * pic : http://img4.imgtn.bdimg.com/it/u=3547796456,889162116&fm=23&gp=0.jpg
             * live_name : 2
             7

             */

            private int status;
            private int live_type;
            private String pic;
            private String live_name;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getLive_type() {
                return live_type;
            }

            public void setLive_type(int live_type) {
                this.live_type = live_type;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLive_name() {
                return live_name;
            }

            public void setLive_name(String live_name) {
                this.live_name = live_name;
            }
        }
    }


//    /**
//     * result : [{"created_at":1489060143902,"updated_at":1489060981240,"id":1157939005030405,"data":{"status":0,"live_name":"边翠霞直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127919&di=703d167b664bd5fb15951419addb8464&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F7c1ed21b0ef41bd5254b96f553da81cb39db3d92.jpg","live_type":0},"uid":1157677280460801}]
//     * error_code : 0
//     */
//
//    private int error_code;
//    /**
//     * created_at : 1489060143902
//     * updated_at : 1489060981240
//     * id : 1157939005030405
//     * data : {"status":0,"live_name":"边翠霞直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127919&di=703d167b664bd5fb15951419addb8464&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F7c1ed21b0ef41bd5254b96f553da81cb39db3d92.jpg","live_type":0}
//     * uid : 1157677280460801
//     */
//
//    private List<ResultBean> result;
//
//    public int getError_code() {
//        return error_code;
//    }
//
//    public void setError_code(int error_code) {
//        this.error_code = error_code;
//    }
//
//    public List<ResultBean> getResult() {
//        return result;
//    }
//
//    public void setResult(List<ResultBean> result) {
//        this.result = result;
//    }
//
//    public static class ResultBean {
//        private long created_at;
//        private long updated_at;
//        private long id;
//        /**
//         * status : 0
//         * live_name : 边翠霞直播
//         * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127919&di=703d167b664bd5fb15951419addb8464&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F7c1ed21b0ef41bd5254b96f553da81cb39db3d92.jpg
//         * live_type : 0
//         */
//
//        private DataBean data;
//        private long uid;
//
//        public long getCreated_at() {
//            return created_at;
//        }
//
//        public void setCreated_at(long created_at) {
//            this.created_at = created_at;
//        }
//
//        public long getUpdated_at() {
//            return updated_at;
//        }
//
//        public void setUpdated_at(long updated_at) {
//            this.updated_at = updated_at;
//        }
//
//        public long getId() {
//            return id;
//        }
//
//        public void setId(long id) {
//            this.id = id;
//        }
//
//        public DataBean getData() {
//            return data;
//        }
//
//        public void setData(DataBean data) {
//            this.data = data;
//        }
//
//        public long getUid() {
//            return uid;
//        }
//
//        public void setUid(long uid) {
//            this.uid = uid;
//        }
//
//        public static class DataBean {
//            private int status;
//            private String live_name;
//            private String pic;
//            private int live_type;
//
//            public int getStatus() {
//                return status;
//            }
//
//            public void setStatus(int status) {
//                this.status = status;
//            }
//
//            public String getLive_name() {
//                return live_name;
//            }
//
//            public void setLive_name(String live_name) {
//                this.live_name = live_name;
//            }
//
//            public String getPic() {
//                return pic;
//            }
//
//            public void setPic(String pic) {
//                this.pic = pic;
//            }
//
//            public int getLive_type() {
//                return live_type;
//            }
//
//            public void setLive_type(int live_type) {
//                this.live_type = live_type;
//            }
//        }
//    }
}
