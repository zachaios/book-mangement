import './index.css'
import {useState} from 'react'
import {
    MenuFoldOutlined,
    MenuUnfoldOutlined,
    HomeOutlined,
    FileMarkdownOutlined,
  } from '@ant-design/icons';
  import { Layout, Menu, Button, theme } from 'antd';
import Book from '../Book';
import { Welcome } from '../welcome';
  
const { Header, Sider, Content } = Layout;

const Home = () => {
    const [collapsed, setCollapsed] = useState(false);
    const {
      token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    const [currentPage,setCurrentPage] = useState('welcome')
    return (
        <div>
            <Layout className="tabContainer">
                <Sider trigger={null} collapsible collapsed={collapsed}>
                <div className="demo-logo-vertical" />
                <Menu
                    theme="dark"
                    mode="inline"
                    defaultSelectedKeys={['welcome']}
                    items={[
                    {
                        key: 'welcome',
                        icon: <HomeOutlined />,
                        label: 'Welcome',
                    },
                    {
                        key: 'book',
                        icon: <FileMarkdownOutlined />,
                        label: 'Book management',
                    },
                    ]}
                    onSelect={(item)=>setCurrentPage(item.key)}
                />
                </Sider>
                <Layout>
                <Header style={{ padding: 0, background: colorBgContainer }}>
                    <Button
                    type="text"
                    icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
                    onClick={() => setCollapsed(!collapsed)}
                    style={{
                        fontSize: '16px',
                        width: 64,
                        height: 64,
                    }}
                    />
                </Header>
                <Content
                    style={{
                    margin: '24px 16px',
                    padding: 24,
                    minHeight: 280,
                    background: colorBgContainer,
                    borderRadius: borderRadiusLG,
                    }}
                >
                    {
                        currentPage=='welcome'&&<Welcome/>
                    }
                    {
                        currentPage=='book'&&<Book/>
                    }
                </Content>
                </Layout>
            </Layout>
        </div>
    );
};

export default Home;
