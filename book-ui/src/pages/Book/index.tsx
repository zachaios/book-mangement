import { useEffect, useState } from "react";
import {
  Table,
  Form,
  Input,
  Popconfirm,
  Typography,
  Button,
  Space,
  DatePicker,
  message,
} from "antd";
import {ColumnsType} from 'antd/es/table/InternalTable'
import {
  BookDataType,
  featchBookByIdApi,
  saveAndUpdateBookApi,
  deleteBookApi,
  allBookApi,
} from "@/apis/home";
import dayjs from "dayjs";


interface Item {
    bookId: string
    bookName: string
    bookDesc: string
    authorName: string
    publishTime: Date
}

const dateFormat = "YYYY-MM-DD";


interface EditableCellProps extends React.HTMLAttributes<HTMLElement> {
  editing: boolean;
  dataIndex: string;
  title: string;
  inputType: string;
  record: Item;
  index: number;
  children: React.ReactNode;
}

const EditableCell: React.FC<EditableCellProps> = ({
  editing,
  dataIndex,
  title,
  inputType,
  record,
  index,
  children,
  ...restProps
}) => {
  const inputNode = inputType === "date" ? <DatePicker defaultValue={dayjs(record?.publishTime, dateFormat)} format={dateFormat}  /> : <Input />;
  return (
    <td {...restProps}>
      {editing ? (
        <Form.Item
          name={dataIndex}
          style={{ margin: 0 }}
          rules={[
            {
              required: true,
              message: `Please Input ${title}!`,
            },
          ]}
        >
          {inputNode}
        </Form.Item>
      ) : (
        children
      )}
    </td>
  );
};

const Book = () => {
  const [messageApi, contextHolder] = message.useMessage();

  const error = (errInfo: string) => {
    messageApi.open({
      type: "error",
      content: errInfo,
    });
  };

  const [bookList, setBookList] = useState<BookDataType[]>([]);
  // 查询书籍
  const searchBook = async () => {
    try {
      if (bookId) {
        const res = await featchBookByIdApi(bookId);
        res.data.key = res.data.bookId;
        // const thisData: any[] = new Array(res.data);
        // setBookList(thisData)
        setBookList(new Array(res.data));
      } else {
        const res = await allBookApi();
        res.data.map((item:BookDataType) => {
          item.key = item.bookId;
        });
        setBookList(res.data);
      }
      setEditingKey("");
    } catch (er) {
      error("WRONG BOOK ID");
    }
  };

  // 初始化调用方法
  useEffect(() => {
    searchBook();
  }, []);

  const [bookId, setBookId] = useState("");

  const [form] = Form.useForm();
  const [editingKey, setEditingKey] = useState<string|null>("");

  const isEditing = (record: BookDataType) => record.key === editingKey;

  const edit = (record: BookDataType) => {
    // debugger
    // console.log(record)
    form.setFieldsValue({ ...record, publishTime: dayjs(record?.publishTime, dateFormat) });
    // form.setFieldValue({ bookNamw: "", authorName: "", bookDesc: "", ...record });
    setEditingKey(record.key);
  };

  const cancel = () => {
    setEditingKey("");
  };

  const save = async () => {
    try {
      await form.validateFields()
      const row = (await form.getFieldsValue(true)) as BookDataType;
      await saveAndUpdateBookApi(row);
      // 修改完之后查询
      searchBook();
    } catch (errInfo) {
      error("SAVE FAILED");
    }
  };

  const deleteBook = async (bookId: string) => {
    if (bookId != null || bookId != undefined) {
      console.log("laile");
      await deleteBookApi(bookId);
    }
    searchBook();
  };

  // 添加书籍
  const addBook = () => {
    // const nowDate = dayjs(new Date(),dateFormat)
    const newBook: BookDataType = {
        key: "addBook",
        bookName: "book name",
        authorName: "author name",
        bookDesc: "book description",
        publishTime: new Date(),
        bookId: null
    };
    // const arr = bookList as BookDataType[]
    // arr.push(newBook)
    // console.log(arr)
    setBookList([newBook,...bookList]);
  };

  const columns = [
    {
      title: "Book Id",
      dataIndex: "bookId",
      width: "20%",
      editable: false,
    },
    {
      title: "Book Name",
      dataIndex: "bookName",
      width: "8%",
      editable: true,
    },
    {
      title: "Author Name",
      dataIndex: "authorName",
      width: "8%",
      editable: true,
    },
    {
      title: "Book Description",
      dataIndex: "bookDesc",
      width: "45%",
      editable: true,
    },
    {
      title: "Publish Time",
      dataIndex: "publishTime",
      width: "10%",
      editable: true,
      render: (_: Date) => {
        // console.log("-=====",_)
        return (
          <Space direction="vertical">
            <DatePicker
              defaultValue={dayjs(_, dateFormat)}
              format={dateFormat}
              disabled
            />
          </Space>
        );
      },
    },
    {
      title: "operation",
      dataIndex: "operation",
      render: (_: Date, record: BookDataType) => {
        console.log(_)
        const editable = isEditing(record);
        return editable ? (
          <span>
            <Typography.Link onClick={() => save()} style={{ marginRight: 8 }}>
              Save
            </Typography.Link>
            <Popconfirm title="Sure to cancel?" onConfirm={cancel}>
              <a>Cancel</a>
            </Popconfirm>
          </span>
        ) : (
          <>
            <Typography.Link
              disabled={editingKey !== ""}
              onClick={() => edit(record)}
            >
              Edit
            </Typography.Link>
          </>
        );
      },
    },
    {
      title: "operation",
      dataIndex: "operation",
      render: ( _:string|undefined,record: BookDataType) => {
        return (
          <Popconfirm
            title="Sure to delete?"
            onConfirm={() => deleteBook(record.bookId!)}
          >
            <a>Delete</a>
          </Popconfirm>
        );
      },
    },
  ];

  const mergedColumns:ColumnsType = columns.map((col) => {
    if (!col.editable) {
      return col;
    }
    return {
      ...col,
      onCell: (record: Item) => ({
        record,
        // inputType: col.dataIndex === "text",
        inputType: col.dataIndex === "publishTime" ? "date" : "text",
        dataIndex: col.dataIndex,
        title: col.title,
        editing: isEditing(record as BookDataType),
      }),
    };
  });

  return (
    <Form form={form} component={false}>
      {contextHolder}
      <Space.Compact style={{ width: "30%" }}>
        <Input
          placeholder="Book id"
          value={bookId}
          onChange={(e) => setBookId(e.target.value)}
        />
        <Button
          type="primary"
          onClick={() => {
            searchBook();
          }}
        >
          Search
        </Button>
        <Button onClick={addBook} type="primary">
          Add a row
        </Button>
      </Space.Compact>
      <Table
        components={{
          body: {
            cell: EditableCell,
          },
        }}
        bordered
        dataSource={bookList}
        columns={mergedColumns}
        rowClassName="editable-row"
        pagination={{
          onChange: cancel,
        }}
        scroll={{ x: 1540, y: 500 }}
      />
    </Form>
  );
};

export default Book;
